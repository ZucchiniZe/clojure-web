(ns web-projects.components
  (:require [web-projects.util :as util]
            [re-frame.core :as rf]
            [reagent.core :as r]))

(defmulti input (fn [x] (:comp x)))
(defmethod input :perm [a]
  (let [{:keys [n k]} (:data a)]
    [:span
     [:input.permutation {:type "number"
                          :value @n
                          :on-change #(rf/dispatch [:set-perm :n (-> % .-target .-value)])}]
     "P"
     [:input.permutation {:type "number"
                          :value @k
                          :on-change #(rf/dispatch [:set-perm :k (-> % .-target .-value)])}]]))
(defmethod input :palindrome [a]
  (let [val (:input a)]
    [:input.palindrome {:type "text"
                        :value @val
                        :on-change #(rf/dispatch [:set-palindrome (-> % .-target .-value)])}]))
(defmethod input :fizzbuzz [a]
  (let [val (:input a)]
    [:input.fizzbuzz {:type "number"
                      :value @val
                      :on-change #(rf/dispatch [:set-fizzbuzz (-> % .-target .-value)])}]))

;; -------------------------
;; Permutation

(defn permutation []
  (let [n (rf/subscribe [:permutation :n])
        k (rf/subscribe [:permutation :k])]
    (fn []
      [:div.permutation
       [:h3 "Permutation/factorial experiemtn!"]
       [:p "this one was really hard. make a permutation"]
       [input {:comp :perm :data {:n n :k k}}]
       ;; fix the browser crashing when k > n and k == 0
       [:p " = " (util/permutation @n @k)]
       [:p @n "! = " (util/factorial @n)]])))

;; -------------------------
;; Palindrome

(defn palindrome []
  (let [word (rf/subscribe [:palindrome])]
    (fn []
      [:div.palindrome
       [:h3 "Palindrome experiment"]
       [:p "check if your word is a palindrome"]
       [:p "is " [input {:comp :palindrome
                         :input word}] " a palindrome? "
        (if (util/palindrome? @word) "yes" "no")]])))

;; -------------------------
;; Fizzbuzz

(defn fizzbuzz-list [items]
  [:ol
   (for [item items]
     ^{:key item} [:li (util/fizzbuzz (+ 1 item))])])

(defn fizzbuzz []
  (let [fizz (rf/subscribe [:fizzbuzz])]
    (fn []
      [:div.fizzbuzz
       [:h3 "Fizzbuzz experiment!"]
       [:p "Here is a fizzbuzz for " [input {:comp :fizzbuzz :input fizz}] " numbers"]
       [fizzbuzz-list (range @fizz)]])))
