(define-fun checkSecure ((p String)) Bool
  (ite (< (str.len p) 5) false 
    (ite (not (or (= (str.contains p "$") true) (= (str.contains p "!") true))) false
      (ite (not (or (= (str.contains p "0") true) (= (str.contains p "9") true))) false
        true
      ) 
    ) 
  )
)
(declare-const x String)
(push)
(assert (= (checkSecure x) true))
(check-sat)
(get-model)
(pop)
(assert (= (checkSecure x) false))
(check-sat)
(get-model)