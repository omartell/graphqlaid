(ns ^:figwheel-hooks graphqlaid.app
  (:require [reagent.core :as reagent]
            [graphqlaid.views.home :as home]))

(def app (js/document.getElementById "app"))

(defn- mount-app []
  (reagent/render [home/view] app))

(defn- init []
  (mount-app))

(defn ^:after-load on-figwheel-reload []
  (mount-app))

(.addEventListener js/document "DOMContentLoaded" init)
