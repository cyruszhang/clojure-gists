(ns
  ^{:doc "A function to expose all methods to implement when realizing an inteface/protocol in deftype. Big thanks to Christophe Grand - https://\
groups.google.com/d/msg/clojure/L1GiqSyQVVg/m-WJogaqU8sJ"
    :author "Christophe Grand"}
  srs.common.base.scaffold
  )

(defn scaffold [iface]
  "A function to expose all methods to implement when realizing an inteface/protocol in deftype"
  (doseq [[iface methods] (->> iface .getMethods
                            (map #(vector (.getName (.getDeclaringClass %))
                                    (symbol (.getName %))
                                    (count (.getParameterTypes %))))
                            (group-by first))]
    (println (str " " iface))
    (doseq [[_ name argcount] methods]
      (println
        (str " "
          (list name (into ['this] (take argcount (repeatedly gensym)))))))))