(ns web-projects.handlers
  (:require [re-frame.core :as rf]
            [web-projects.db :as db]))

;; application handlers

(rf/register-handler
 :initialize-db
 (fn [_ _]
   db/default-db))

(rf/register-handler
 :set-active-panel
 (fn [db [_ active-panel]]
   (assoc db :active-panel active-panel)))

;; component handlers

(rf/register-handler
 :set-name
 (fn [db [_ new-name]]
   (assoc db :name new-name)))

(rf/register-handler
 :set-fizzbuzz
 (fn [db [_ new-number]]
   (assoc db :fizzbuzz new-number)))

(rf/register-handler
 :set-palindrome
 (fn [db [_ new-word]]
   (assoc db :palindrome new-word)))

(rf/register-handler
 :set-perm
 (fn [db [_ key num]]
   ;; (println key (-> db :permutation key))
   (assoc-in db [:permutation key] (js/parseInt num))))
