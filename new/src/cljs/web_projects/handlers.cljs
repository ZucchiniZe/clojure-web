(ns web-projects.handlers
  (:require [re-frame.core :as re-frame]
            [web-projects.db :as db]))

;; application handlers

(re-frame/register-handler
 :initialize-db
 (fn [_ _]
   db/default-db))

(re-frame/register-handler
 :set-active-panel
 (fn [db [_ active-panel]]
   (assoc db :active-panel active-panel)))

;; component handlers

(re-frame/register-handler
 :set-name
 (fn [db [_ new-name]]
   (assoc db :name new-name)))

(re-frame/register-handler
 :set-fizzbuzz
 (fn [db [_ new-number]]
   (assoc db :fizzbuzz new-number)))

(re-frame/register-handler
 :set-palindrome
 (fn [db [_ new-word]]
   (assoc db :palindrome new-word)))
