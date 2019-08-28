(ns user
  (:require
    [graphqlaid.service :as service]
    [figwheel.main.api :as fapi]
    [com.walmartlabs.lacinia :as lacinia]
    [com.walmartlabs.lacinia.pedestal :as lp]
    [io.pedestal.http :as http]
    [clojure.tools.namespace.repl :as repl :refer [refresh set-refresh-dirs]]
    [clojure.java.browse :refer [browse-url]]
    [clojure.walk :as walk])
  (:import (clojure.lang IPersistentMap)))

;;(repl/disable-reload!)

(set-refresh-dirs "dev" "src" "test")

(def schema (service/load-schema))

(defn simplify
  "Converts all ordered maps nested within the map into standard hash maps, and
   sequences into vectors, which makes for easier constants in the tests, and eliminates ordering problems."
  [m]
  (walk/postwalk
    (fn [node]
      (cond
        (instance? IPersistentMap node)
        (into {} node)

        (seq? node)
        (vec node)

        :else
        node))
    m))

(defn q
  [query-string]
  (-> (lacinia/execute schema query-string nil nil)
      simplify))

(defonce server nil)

(defn start-server [_]
  (let [server (-> schema
                   (lp/service-map {:graphiql true})
                   http/create-server
                   http/start)]
    server))

(defn browse []
  (browse-url "http://localhost:8888/"))

(defn stop-server
  [server]
  (http/stop server)
  nil)

(defn start []
  (alter-var-root #'server start-server)
  :started)

(defn stop []
  (alter-var-root #'server stop-server)
  :stopped)

(defn reset []
  (when server (stop))
  (refresh :after 'user/start))

(defn start-figwheel []
  (fapi/start :dev))

(defn stop-figwheel []
  (fapi/stop))

(defn cljs
  ([] (cljs :dev))
  ([build-id] (fapi/cljs-repl (name build-id))))

(comment
  (q "query {helloWorld}"))
