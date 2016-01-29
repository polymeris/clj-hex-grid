(ns clj-hex-grid.round)

(defn- x_diff_is_largest?
  [diff_x diff_y diff_z]
  (and (> diff_x diff_y)
       (> diff_x diff_z)))

(defn- y_diff_is_largest?
  [diff_x diff_y diff_z]
  (and (> diff_y diff_x)
       (> diff_y diff_z)))

(defn- reset_coordinate_with_largest_diff_to_round
  [diff_x diff_y diff_z rounded_x rounded_y rounded_z]
  (cond
    (x_diff_is_largest? diff_x diff_y diff_z) {:x (- (- rounded_y) rounded_z)
                                               :y rounded_y
                                               :z rounded_z}
    (y_diff_is_largest? diff_x diff_y diff_z) {:x rounded_x
                                               :y (- (- rounded_x) rounded_z)
                                               :z rounded_z}
    :else {:x rounded_x
           :y rounded_y
           :z (- (- rounded_x) rounded_y)}))

(defn cube-round
  "var rx = round(h.x)
   var ry = round(h.y)
   var rz = round(h.z)
   var x_diff = abs(rx - h.x)
   var y_diff = abs(ry - h.y)
   var z_diff = abs(rz - h.z)
   if x_diff > y_diff and x_diff > z_diff:
       rx = -ry-rz
   else if y_diff > z_diff:
       ry = -rx-rz
   else:
       rz = -rx-ry
   return Cube(rx, ry, rz)"
  [{^float x :x ^float y :y ^float z :z}]
  (let [rounded_x (Math/round x)
        diff_x (Math/abs (- rounded_x x))
        rounded_y (Math/round y)
        diff_y (Math/abs (- rounded_y y))
        rounded_z (Math/round z)
        diff_z (Math/abs (- rounded_z z))]
    (reset_coordinate_with_largest_diff_to_round
      diff_x diff_y diff_z
      rounded_x rounded_y rounded_z)))