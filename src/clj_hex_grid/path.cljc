(ns clj-hex-grid.path
  (:require [#?(:cljs tailrecursion.priority-map
                :clj  clojure.data.priority-map) :refer [priority-map]]
            [clj-hex-grid.distance :as d]
            [clj-hex-grid.neighbours :as n]
            [clj-hex-grid.round :refer [cube-round]]))

(defn- cube_lerp
  "return Cube(a.x + (b.x - a.x) * t,
                a.y + (b.y - a.y) * t,
                a.z + (b.z - a.z) * t)"
  [origin dest scale_factor]
  (let [x (+ (:x origin) (* scale_factor (- (:x dest) (:x origin))))
        y (+ (:y origin) (* scale_factor (- (:y dest) (:y origin))))
        z (+ (:z origin) (* scale_factor (- (:z dest) (:z origin))))
        rounded_lerp (cube-round {:x x :y y :z z})] rounded_lerp))

(defn- scale_factor
  [max_samples current_sample_index]
  (* (/ 1.0 max_samples) current_sample_index))

(defn between
  "var N = cube_distance(a, b)
    var results = []
    for each 0 ≤ i ≤ N:
        results.append(cube_round(cube_lerp(a, b, 1.0/N * i)))
    return results"
  [origin dest]
  (let [number_of_samples (d/cube-between origin dest)
        sample_range (range 0 (+ 1 number_of_samples))
        path (distinct (into [] (map #(cube_lerp origin dest (scale_factor number_of_samples %1))
                                     sample_range)))]
    path))

(defn find-path
  "A* search from start to end."
  [start end cost-fn neighbours-fn heuristic]
  (loop [routes {}
         frontier (priority-map start 0)]
    (if (empty? frontier)
      (get routes end)
      (let [[node _] (first frontier)
            frontier-rest (dissoc frontier node)
            neighbours (neighbours-fn node)
            cheapest-neighbour (first (sort-by :cost (keep #(get routes %) neighbours)))
            oldcost (:cost (get routes node))
            newcost (+ (cost-fn node) (or (:cost cheapest-neighbour) 0))]
        (if (and oldcost (>= newcost oldcost))
          (recur routes frontier-rest)
          (recur (assoc routes node {:cost  newcost
                                     :nodes (conj (:nodes cheapest-neighbour []) node)})
                 (reduce #(assoc %1 %2 (+ newcost (heuristic %2))) frontier-rest neighbours)))))))

(defn cube-find-path
  "A* search asuming cube coordinates. A board is a map of nodes to costs."
  ([start end board]
   (cube-find-path start end #(get board %) #(contains? board %)))
  ([start end cost-fn in-bounds?]
   (find-path start end
              cost-fn
              #(filter in-bounds? (n/cube-neighbours %))
              (partial d/cube-between end))))