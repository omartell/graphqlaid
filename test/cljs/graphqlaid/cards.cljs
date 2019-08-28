(ns graphqlaid.cards
  (:require
   [devcards.core]
   [clojure.spec.test.alpha :as st]
   [graphqlaid.tests]))

(st/instrument)

(devcards.core/start-devcard-ui!)
