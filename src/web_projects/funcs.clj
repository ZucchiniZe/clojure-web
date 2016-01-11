(ns web-projects.funcs)

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
