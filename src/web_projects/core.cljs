(ns web-projects.core
  (:use [jayq.core :only [$ css html]])
  (:require [jayq.core :as jq]))

(enable-console-print!)

(defonce state (atom false))

(def $header ($ :#header>h1))

(jq/bind $header :click
         (fn [evt]
           (html $header (do
                           (swap! state not)
                           (println "swapping state")
                           (if @state "Yes" "No")))))
