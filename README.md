# clj-hex-grid

![clj-hex-grid](https://raw.githubusercontent.com/polymeris/clj-hex-grid/master/resources/clj-hex-grid.png)
[![Build Status](https://snap-ci.com/andeemarks/clj-hex-grid/branch/master/build_image)](https://snap-ci.com/andeemarks/clj-hex-grid/branch/master)
[![Clojars Project](https://img.shields.io/clojars/v/clj-hex-grid.svg)](https://clojars.org/clj-hex-grid)

A Clojure library implementing the algorithms specified in http://www.redblobgames.com/grids/hexagons/.

| Feature                   | Cube | Offset | Axial | Flat | Pointy |
| ------------------------- |:----:|:------:|:-----:|:----:|:------:|
| Angles, size, spacing     | ✓    | ✓      | ✓     | ✓    | ✓      |
| Coordinate conversions    | ✓    | ✓      |       |      |        |
| Neighbours†               | ✓    | ✓      |       | ✓    |        |
| Distances                 | ✓    | ✓      |       | ✓    |        |
| Range                     |      |        |       |      |        |
| Rotation                  |      |        |       |      |        |
| Rings & spirals           | ✓    |        |       | ✓    |        |
| Line drawing              | ✓    |        |       | ✓    | ✓      |
| Field of View‡            | ✓    |        |       | ✓    |        |
| Hex to pixel & viceversa  | ✓    |        |       | ✓    |        |
| Pathfinding               | ✓    | ✓      | ✓     | ✓    | ✓      |
| Wraparound maps           |      |        |       |      |        |

† No diagonals
‡ Small FOV only

## License

Copyright © 2015 FIXME

Distributed under the Eclipse Public License, the same as Clojure.
