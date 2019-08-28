(defproject graphqlaid "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url         "http://example.com/FIXME"
  :license     {:name "Eclipse Public License"
                :url  "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/clojurescript "1.10.439"]
                 [org.clojure/spec.alpha "0.2.176"]
                 [aero "1.1.2"]
                 [integrant "0.6.1"]
                 [re-frame "0.10.6"]
                 [reagent "0.8.1"]
                 [integrant/repl "0.3.1"]
                 [com.walmartlabs/lacinia-pedestal "0.12.0" :exclusions [io.pedestal/pedestal.service
                                                                        io.pedestal/pedestal.jetty]]
                 [io.pedestal/pedestal.service       "0.5.5"]
                 [io.pedestal/pedestal.jetty         "0.5.5"]]

  :source-paths ["src" "src/cljs" "src/clj" "src/cljc"]

  :profiles {:uberjar {:aot :all}
             :dev     {:dependencies   [[com.bhauman/figwheel-main "0.2.3"]
                                        [com.bhauman/rebel-readline-cljs "0.1.4"]
                                        [devcards "0.2.6"]
                                        [binaryage/devtools "0.8.3"]
                                        [day8.re-frame/test "0.1.5"]
                                        [org.clojure/test.check "0.10.0-alpha3"]]
                       :source-paths   ["dev" "test" "test/cljs" "test/cljc" "test/clj"]
                       :resource-paths ["target"]
                       :clean-targets  ^{:protect false} ["target"]}}

  :aliases {"fig"       ["trampoline" "run" "-m" "figwheel.main"]
            "fig:build" ["trampoline" "run" "-m" "figwheel.main" "-b" "dev" "-r"]
            "fig:min"   ["run" "-m" "figwheel.main" "-O" "advanced" "-bo" "dev"]
            "fig:test"  ["run" "-m" "figwheel.main" "-co" "test.cljs.edn" "-m" graphqlaid.runner]}

  :main ^:skip-aot graphqlaid.core
  :target-path "target/%s")
