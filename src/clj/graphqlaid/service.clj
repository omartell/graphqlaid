(ns graphqlaid.service
  (:require
   [clojure.edn :as edn]
   [clojure.java.io :as io]
   [com.walmartlabs.lacinia.schema :as schema]
   [com.walmartlabs.lacinia.util :refer [attach-resolvers]]))

(defn hello-world [context _ _]
  "Hello!")

(def resolvers
  {:hello-world hello-world})

(defn load-schema []
  (-> (io/resource "schema.edn")
      slurp
      edn/read-string
      (attach-resolvers resolvers)
      (schema/compile {:default-field-resolver schema/hyphenating-default-field-resolver})))
