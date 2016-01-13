(ns web-projects.core)

(enable-console-print!)

(println "testing")

(defn factorial
  "compute a factorial"
  [num]
  (loop [cnt num sum 1]
    (if (zero? cnt)
      sum
      (recur (dec cnt) (* cnt sum)))))

(println "5! =" (factorial 5))
