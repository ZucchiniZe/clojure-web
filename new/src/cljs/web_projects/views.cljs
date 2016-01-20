(ns web-projects.views
  (:require [re-frame.core :as rf]
            [web-projects.config :as config]
            [web-projects.components :as comp]))

;; Home

(defn home-panel []
  (let [name (rf/subscribe [:name])]
    (fn []
      [:div
       [:span  "Hello from " @name ". This is the home page. "
        [:a {:href "/experiments"} "go to the experiments page."]]
       [:input#name {:type "text" :placeholder "chamge name to..."}]
       [:button {:on-click #(rf/dispatch [:set-name (.-value (.getElementById
                                                              js/document "name"))])} "change name"]])))

;; Experiments

(defn experiments-panel []
  (fn []
    [:div
     [comp/palindrome]
     [comp/permutation]
     [comp/fizzbuzz]
     [comp/array-adder]]))

;; Main

(defmulti panels identity)
(defmethod panels :home [] [home-panel])
(defmethod panels :experiments [] [experiments-panel])
(defmethod panels :default [] [:div])

(defn main-panel []
  (let [active-panel (rf/subscribe [:active-panel]) db (rf/subscribe [:database])]
    (fn []
      [:div
       (if config/debug?
         [comp/dev-hud db])
       [:h1 "Web Projects > " (clj->js @active-panel)]
       [panels @active-panel]])))
