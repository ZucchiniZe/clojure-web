(ns web-projects.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]
            [web-projects.util :as util]
            [web-projects.components :as comp]
            [web-projects.handlers]
            [web-projects.subs]
            [web-projects.routes :as routes]
            [web-projects.views :as views]
            [web-projects.config :as config]))

(when config/debug?
  (println "dev mode"))

(defn mount-root []
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init! []
  (routes/app-routes)
  (rf/dispatch-sync [:initialize-db])
  (mount-root))
