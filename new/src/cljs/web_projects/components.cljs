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
(defmethod input :array [a]
  (let [val (:input a)]
    [:input.array-adder {:type "text"
                         :value @val
                         :on-change #(rf/dispatch [:set-array (-> % .-target .-value)])}]))

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
       [:p [:sub @n] "P" [:sub @k] " = " (util/permutation @n @k)]
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

;; -------------------------
;; Array adder

(defn array-adder []
  (let [array (rf/subscribe [:array])
        compl-array (rf/subscribe [:compl-array])]
    (fn []
      [:div.arrayadder
       [:h3 "Array adding experiment"]
       [:p "please enter a list of numbers seperated by space or comma" [input {:comp :array :input array}]]
       [:p (apply str (interpose " + " @compl-array)) " = " (apply + @compl-array)]])))

;; -------------------------
;; Devmode

(defn dev-hud [db]
  [:pre>code {:style {:position "fixed"
                      :bottom 0
                      :left 5}}
   [:p {:style {:font-weight "bold"}} "debug menu "
    [:button {:on-click #(rf/dispatch [:initialize-db])} "reset db"]]
   (.stringify js/JSON (clj->js @db) nil 2)])
