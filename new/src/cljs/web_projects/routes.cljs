(ns web-projects.routes
  (:require-macros [secretary.core :refer [defroute]])
  (:require [secretary.core :as secretary]
            [re-frame.core :as rf]
            [accountant.core :as accountant]))

(defn app-routes []
  ;; Routes go here
  (defroute "/" []
    (rf/dispatch [:set-active-panel :home]))
  (defroute "/experiments" []
    (rf/dispatch [:set-active-panel :experiments]))

  (accountant/configure-navigation!)
  (accountant/dispatch-current!))
