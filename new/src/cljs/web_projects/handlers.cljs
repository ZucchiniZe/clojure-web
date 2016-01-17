(ns web-projects.handlers
  (:require [re-frame.core :as re-frame]
            [web-projects.db :as db]))

(re-frame/register-handler
 :initialize-db
 (fn [_ _]
   db/default-db))

(re-frame/register-handler
 :set-active-panel
 (fn [db [_ active-panel]]
   (assoc db :active-panel active-panel)))

(re-frame/register-handler
 :set-name
 (fn [db [_ new-name]]
   (assoc db :name new-name)))
