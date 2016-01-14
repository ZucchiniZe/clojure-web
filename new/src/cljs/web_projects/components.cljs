(ns web-projects.components
  (:require [web-projects.util :as util]
            [reagent.core :as reagent :refer [atom]]))

;; -------------------------
;; Permutation

(defn perm-input
  "input for N"
  ([val]
   [:input.permutation {:type "number"
                        :value @val
                        :on-change #(reset! val (-> % .-target .-value))}])
  ([k n]
   [:input.permutation {:type "number"
                        :value @k
                        :on-change (fn [evt]
                                     (if (< @k @n)
                                       (reset! k (-> evt .-target .-value))
                                       (reset! k (- @n 1))))}]))

(defn permutation []
  (let [n (atom 10) k (atom 1)]
    (fn []
      [:div.permutation
       [:p "make a permutation"]
       [:span [perm-input n] "P" [perm-input k n]]
       ;; fix the browser crashing when k > n and k == 0
       [:p " = " (util/permutation @n @k)]])))

;; -------------------------
;; Palindrome

(defn pal-input [val]
  [:input.palindrome {:type "text"
                      :value @val
                      :on-change #(reset! val (-> % .-target .-value))}])

(defn palindrome []
  (let [word (atom "racecar")]
    (fn []
      [:div.palindrome
       [:p "check if your word is a palindrome"]
       [:p "is " [pal-input word] " a palindrome? " (if (util/palindrome? @word) "yes" "no")]])))

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
       [:p "Here is a fizzbuzz for " [fizz-input fizz] " numbers"]
       [fizzbuzz-list (range @fizz)]])))
