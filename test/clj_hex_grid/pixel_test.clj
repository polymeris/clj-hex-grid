(ns clj-hex-grid.pixel-test
  (:require [clj-hex-grid.pixel :as pixel])
  (:use [midje.sweet]))

(def sizes [1 -5 100])

(def pixels (for [x (range -100 100 17), y (range -100 100 27)]
              [x y]))

(def hexes (for [x (range -3 3), y (range -3 3)]
             {:x x :y y :z (+ x y)}))

(facts "when converting from pixels to hexes and back"
       (fact "output in radius size of input"
             (every? identity
                     (for [s sizes, p pixels]
                       (let [[x0 y0] p
                             hex (pixel/pixel->cube s p)
                             [x y] (pixel/cube->pixel s hex)
                             dx (- x0 x)
                             dy (- y0 y)
                             distance (Math/sqrt (+ (* dx dx) (* dy dy)))]
                         (<= -1 (/ distance s) 1)))) => true))

(facts "when converting from hexes to pixels and back"
       (fact "output equals input"
             (every? identity
                     (for [s sizes, h hexes]
                       (= (pixel/pixel->cube s (pixel/cube->pixel s h)) h))) => true))
