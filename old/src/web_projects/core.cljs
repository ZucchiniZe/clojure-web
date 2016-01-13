(ns web-projects.core
  (:use [jayq.core :only [$ css html]])
  (:require [jayq.core :as jq]))

(enable-console-print!)

(defonce state (atom {:yes false}))

(def $header ($ :#header>h1))

(jq/bind $header :click
         (fn [evt]
           (html $header (do
                           (swap! state update-in [:yes] not)
                           (println "swapping @state" (:yes @atom))
                           (if @state "Yes" "No")))))
