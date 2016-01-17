(ns web-projects.views
  (:require [re-frame.core :as re-frame]
            [web-projects.components :as comp]))

;; Home

(defn home-panel []
  (let [name (re-frame/subscribe [:name])]
    (fn []
      [:div "Hello from " @name ". This is the home page. "
       [:a {:href "/experiments"} "go to the experiments page."]
       [:input#name {:type "text" :placeholder "chamge name to..."}]
       [:button {:on-click #(re-frame/dispatch [:set-name (.-value (.getElementById
                                                                    js/document "name"))])} "change name"]])))

;; Experiments

(defn experiments-panel []
  [:div "placeholder for now"]
  [:div [:h2 "Assorted clojurescript examples"]
   [comp/palindrome]
   [comp/permutation]
   [comp/fizzbuzz]])

;; Main

(defmulti panels identity)
(defmethod panels :home-panel [] [home-panel])
(defmethod panels :experiments-panel [] [experiments-panel])
(defmethod panels :default [] [:div])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [:active-panel])]
    (fn []
      (panels @active-panel))))
