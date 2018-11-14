(define-fun lib1 ((x Int)) Int
  (ite (> x 10) 11 x)
)

(define-fun foo ((x Int)) Int
  (ite (> x (lib1 x)) x (lib1 x))
)

(declare-const x Int)
(assert (= (foo x) 12))
(check-sat)
(get-model)
