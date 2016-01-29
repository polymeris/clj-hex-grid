(ns clj-hex-grid.pixel
  (:require [clj-hex-grid.round :refer [cube-round]]))

(defn cube->pixel [size {x :x y :y}]
  [(* size 1.5 x)
   (* size
      (Math/sqrt 3)
      (+ (/ x 2) y))])

(defn pixel->cube [size [px-x px-y]]
  (let [->int (fn [^double x] (-> x Math/round int))        ;TODO: Use the rounding algo?
        x (->int (/ px-x size 1.5))
        y (->int (- (/ px-y size (Math/sqrt 3))
             (/ x 2)))]
    {:x x
     :y y
     :z (+ x y)}))

;(defn pixel->cube [size [px-x px-y]]
;  (let [x (/ px-x size 1.5)
;        y (- (/ px-y size (Math/sqrt 3))
;             (/ x 2))
;        z (+ x y)]
;    (cube-round {:x x :y y :z z})))