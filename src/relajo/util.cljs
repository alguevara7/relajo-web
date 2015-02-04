(ns relajo.util)

(defn count-in [coll f v]
  (count (filter #(= (f %) v) coll)))
