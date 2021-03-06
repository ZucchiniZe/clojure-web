(ns web-projects.util
  (:require [clojure.string]))

(defn fizzbuzz
  "Find the fizzbuzz for a number"
  [num]
  (let [three (rem num 3) five (rem num 5)]
    (cond
      (and
       (= three 0)
       (= five 0)) "FizzBuzz"
      (= three 0) "Fizz"
      (= five 0) "Buzz"
      :else "none")))

(defn palindrome?
  "Check if a word is a palindrome"
  [word]
  (= word (clojure.string/reverse word)))

(defn factorial
  "Solve a factorial from a given number"
  [number]
  (loop [cnt number product 1]
    (if (zero? cnt)
      product
      (recur (dec cnt) (* cnt product)))))

(defn permutation
  "Solve a permutation given 2 numbers"
  [n k]
  (if (and (<= k n) (>= k 0))
    (/ (factorial n) (factorial (- n k)))
    "not calculable"))
