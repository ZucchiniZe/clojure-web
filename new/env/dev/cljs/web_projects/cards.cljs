(ns web-projects.cards
  (:require [reagent.core :as reagent :refer [atom]]
            [web-projects.core :as core]
            [web-projects.components :as comp])
  (:require-macros
   [devcards.core
    :as dc
    :refer [defcard defcard-doc defcard-rg deftest]]))

(defcard-rg home-page
  [core/home-page])

(defcard-rg permutation
  [comp/permutation])

(defcard-rg palindrome
  [comp/palindrome])

(defcard-rg fizzbuzz
  [comp/fizzbuzz])

(reagent/render [:div] (.getElementById js/document "app"))

;; remember to run 'lein figwheel devcards' and then browse to
;; http://localhost:3449/cards
