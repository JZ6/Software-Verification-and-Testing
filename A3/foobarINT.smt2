(define-fun lib1 ((x Int)) Int
  (ite (> x 10) 11 x)
)

(define-fun foo ((x Int)) Int
  (ite (> x (lib1 x)) x (lib1 x))
)

(define-fun lib2 ((x Int)) Int
  (ite (> x 10) 11 (- x 1))
)

(define-fun bar ((x Int)) Int
  (ite (> x (lib2 x)) x (lib2 x))
)

(declare-const x Int)
(assert (= (foo x) (bar x)))
(check-sat)
(get-model)
