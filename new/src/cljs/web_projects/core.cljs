(ns web-projects.core
  (:require [web-projects.util :as util]
            [reagent.core :as reagent :refer [atom]]
            [reagent.session :as session]
            [secretary.core :as secretary :include-macros true]
            [accountant.core :as accountant]))

;; -------------------------
;; Globals

(def projects [["/f/100" "a fizzbuzz for 100 numbers"]
               ["/p/racecar" "a palindrome checker"]
               ["/m/p/10..3" "a permutation solver"]])

;; -------------------------
;; Views

(defn home-page []
  [:div [:h2 "Welcome to web-projects"]
   [:p "this is a tiny website where I put my projects while learning clojure(script)"]
   [:ul
    (for [project projects]
      ^{:key project} [:li [:a {:href (first project)} (last project)]])]])

(defn about-page []
  [:div [:h2 "About web-projects"]
   [:div [:a {:href "/"} "go to the home page"]]])

(defn perm-input [val]
  [:input {:type "number"
           :value @val
           :on-change #(reset! val (-> % .-target .-value))
           :style {:width 40}}])

(defn permutation-page []
  (let [n (atom 10) k (atom 1)]
    (fn []
      [:div.permutation
       [:p "make a permutation"]
       [:sub  [perm-input n]] "P" [:sub [perm-input k]]
       ;; fix the browser crashing when k > n and k == 0
       [:p " = " (util/permutation @n @k)]])))

(defn current-page []
  [:div [(session/get :current-page)]])

;; -------------------------
;; Routes

(secretary/defroute "/" []
  (session/put! :current-page #'home-page))

(secretary/defroute "/about" []
  (session/put! :current-page #'about-page))

;; -------------------------
;; Initialize app

(defn mount-root []
  (reagent/render [current-page] (.getElementById js/document "app")))

(defn init! []
  (accountant/configure-navigation!)
  (accountant/dispatch-current!)
  (mount-root))
