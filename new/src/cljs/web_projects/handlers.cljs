(ns web-projects.handlers
  (:require [re-frame.core :as rf]
            [web-projects.db :as db]))

;; application handlers

(rf/register-handler
 :initialize-db
 rf/trim-v
 (fn []
   db/default-db))

(rf/register-handler
 :set-active-panel
 rf/trim-v
 (fn [db [active-panel]]
   (assoc db :active-panel active-panel)))

;; component handlers

(rf/register-handler
 :set-name
 rf/trim-v
 (fn [db [new-name]]
   (assoc db :name new-name)))

(rf/register-handler
 :set-fizzbuzz
 rf/trim-v
 (fn [db [new-number]]
   (assoc db :fizzbuzz (js/parseInt new-number))))

(rf/register-handler
 :set-palindrome
 rf/trim-v
 (fn [db [new-word]]
   (assoc db :palindrome new-word)))

(rf/register-handler
 :set-perm
 rf/trim-v
 (fn [db [key num]]
   ;; (println key (-> db :permutation key))
   (assoc-in db [:permutation key] (js/parseInt num))))

(rf/register-handler
 :set-array
 rf/trim-v
 (fn [db [new-array]]
   (assoc db :array new-array)))
