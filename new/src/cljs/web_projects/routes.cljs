(ns web-projects.routes
  (:require-macros [secretary.core :refer [defroute]])
  (:require [secretary.core :as secretary]
            [re-frame.core :as re-frame]
            [accountant.core :as accountant]))

(defn app-routes []
  ;; Routes go here
  (defroute "/" []
    (re-frame/dispatch [:set-active-panel :home-panel]))
  (defroute "/experiments" []
    (re-frame/dispatch [:set-active-panel :experiments-panel]))

  (accountant/configure-navigation!)
  (accountant/dispatch-current!))
