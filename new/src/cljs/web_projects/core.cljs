(ns web-projects.core
  (:require [web-projects.util :as util]
            [web-projects.components :as comp]
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
   [:p "but for now just go to the experiments page " [:a {:href "/experiments"} "over here"]]])

(defn about-page []
  [:div [:h2 "About web-projects"]
   [:div [:a {:href "/"} "go to the home page"]]])

(defn experiments-page []
  [:div [:h2 "Assorted clojurescript experiments"]
   [comp/palindrome]
   [comp/permutation]])

(defn current-page []
  [:div [(session/get :current-page)]])

;; -------------------------
;; Routes

(secretary/defroute "/" []
  (session/put! :current-page #'home-page))

(secretary/defroute "/experiments" []
  (session/put! :current-page #'experiments-page))

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
