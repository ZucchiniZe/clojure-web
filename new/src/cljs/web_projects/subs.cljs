(ns web-projects.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :as re-frame]))

;; applictaion subs

(re-frame/register-sub
 :active-panel
 (fn [db _]
   (reaction (:active-panel @db))))

(re-frame/register-sub
 :database
 (fn [db]
   (reaction @db)))

;; component subs

(re-frame/register-sub
 :name
 (fn [db]
   (reaction (:name @db))))

(re-frame/register-sub
 :fizzbuzz
 (fn [db]
   (reaction (:fizzbuzz @db))))

(re-frame/register-sub
 :palindrome
 (fn [db]
   (reaction (:palindrome @db))))
