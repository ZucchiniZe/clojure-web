(ns web-projects.components
  (:require [web-projects.util :as util]
            [re-frame.core :as re-frame]
            [reagent.core :as r]))

(defmulti input (fn [x] (:comp x)))
(defmethod input :perm [a]
  (let [{:keys [n k]} (:data a)]
    [:span
     [:input.permutation {:type "number"
                          :value @n
                          :on-change #(reset! n (-> % .-target .-value))}]
     "P"
     [:input.permutation {:type "number"
                          :value @k
                          :on-change (fn [evt]
                                       (if (< @k @n)
                                         (reset! k (-> evt .-target .-value))
                                         (reset! k (- @n 1))))}]]))
(defmethod input :palindrome [a]
  (let [val (:input a)]
    [:input.palindrome {:type "text"
                        :value @val
                        :on-change #(re-frame/dispatch [:set-palindrome (-> % .-target .-value)])}]))
(defmethod input :fizzbuzz [a]
  (let [val (:input a)]
    [:input.fizzbuzz {:type "number"
                      :value @val
                      :on-change #(re-frame/dispatch [:set-fizzbuzz (-> % .-target .-value)])}]))

;; -------------------------
;; Permutation

(defn permutation []
  (let [n (r/atom 10) k (r/atom 1)]
    (fn []
      [:div.permutation
       [:p "make a permutation"]
       [input {:comp :perm :data {:n n :k k}}]
       ;; fix the browser crashing when k > n and k == 0
       [:p " = " (util/permutation @n @k)]])))

;; -------------------------
;; Palindrome

(defn palindrome []
  (let [word (re-frame/subscribe [:palindrome])]
    (fn []
      [:div.palindrome
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
  (let [fizz (re-frame/subscribe [:fizzbuzz])]
    (fn []
      [:div.fizzbuzz
       [:p "Here is a fizzbuzz for " [input {:comp :fizzbuzz :input fizz}] " numbers"]
       [fizzbuzz-list (range @fizz)]])))
