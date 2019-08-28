(ns graphqlaid.core-test
  (:require [devcards.core :refer-macros [deftest]]
            [cljs.test :refer [use-fixtures] :refer-macros [testing is]]))

(deftest equal-test?
  (is (= 2 1)))
