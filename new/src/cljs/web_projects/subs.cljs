(ns web-projects.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :as rf]
            [cljs.reader :as reader]))

;; applictaion subs

(rf/register-sub
 :active-panel
 (fn [db _]
   (reaction (:active-panel @db))))

(rf/register-sub
 :database
 (fn [db]
   (reaction @db)))

;; component subs

(rf/register-sub
 :name
 (fn [db]
   (reaction (:name @db))))

(rf/register-sub
 :fizzbuzz
 (fn [db]
   (reaction (:fizzbuzz @db))))

(rf/register-sub
 :palindrome
 (fn [db]
   (reaction (:palindrome @db))))

(rf/register-sub
 :permutation
 (fn [db [_ key]]
   (reaction (-> @db :permutation key))))

(rf/register-sub
 :array
 (fn [db]
   (reaction (:array @db))))

(rf/register-sub
 :compl-array
 (fn [db]
   (reaction (reader/read-string (str "[" (:array @db) "]")))))
