(ns web-projects.components
  (:require [web-projects.util :as util]
            [reagent.core :as reagent :refer [atom]]))

(defmulti input (fn [x] (:comp x)))
(defmethod input :perm-one [a]
  (let [n (:n a)]
    [:input.permutation {:type "number"
                         :value @n
                         :on-change #(reset! n (-> % .-target .-value))}]))
(defmethod input :perm-two [a]
  (let [n (:n a) k (:k a)]
    [:input.permutation {:type "number"
                         :value @k
                         :on-change (fn [evt]
                                      (if (< @k @n)
                                        (reset! k (-> evt .-target .-value))
                                        (reset! k (- @n 1))))}]))
(defmethod input :palindrome [a]
  (let [val (:input a)]
    [:input.palindrome {:type "text"
                        :value @val
                        :on-change #(reset! val (-> % .-target .-value))}]))
(defmethod input :fizzbuzz [a]
  (let [val (:input a)]
    [:input.fizzbuzz {:type "number"
                      :value @val
                      :on-change #(reset! val (-> % .-target .-value))}]))

;; -------------------------
;; Permutation

(defn permutation []
  (let [n (atom 10) k (atom 1)]
    (fn []
      [:div.permutation
       [:p "make a permutation"]
       [:span [input {:comp :perm-one :n n}] "P" [input {:comp :perm-two :n n :k k}]]
       ;; fix the browser crashing when k > n and k == 0
       [:p " = " (util/permutation @n @k)]])))

;; -------------------------
;; Palindrome

(defn palindrome []
  (let [word (atom "racecar")]
    (fn []
      [:div.palindrome
       [:p "check if your word is a palindrome"]
       [:p "is " [input {:comp :palindrome
                         :input word}] " a palindrome? "
        (if (util/palindrome? @word) "yes" "no")]])))

;; -------------------------
;; Fizzbuzz

(defn fizz-input [value]
  [:input.fizzbuzz {:type "number"
                    :value @value
                    :on-change #(reset! value (-> % .-target .-value))}])

(defn fizzbuzz-list [items]
  [:ol
   (for [item items]
     ^{:key item} [:li (util/fizzbuzz (+ 1 item))])])

(defn fizzbuzz []
  (let [fizz (atom 20)]
    (fn []
      [:div.fizzbuzz
       [:p "Here is a fizzbuzz for " [input {:comp :fizzbuzz :input fizz}] " numbers"]
       [fizzbuzz-list (range @fizz)]])))
