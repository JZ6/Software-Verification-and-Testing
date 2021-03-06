(define-fun lib1 ((x (_ BitVec 32))) (_ BitVec 32)
  (ite (bvsgt x (_ bv10 32)) (_ bv11 32) x)
)

(define-fun foo ((x (_ BitVec 32))) (_ BitVec 32)
  (ite (bvsgt x (lib1 x)) x (lib1 x))
)

(define-fun lib2 ((x (_ BitVec 32))) (_ BitVec 32)
  (ite (bvsgt x (_ bv10 32)) (_ bv11 32) (bvsub x (_ bv1 32)))
)

(define-fun bar ((x (_ BitVec 32))) (_ BitVec 32)
  (ite (bvsgt x (lib2 x)) x (lib2 x))
)

(declare-const x (_ BitVec 32))
(assert (not (and (bvsge (foo x) (bar x)) (bvsle (foo x) (bar x)))))
(check-sat)
(get-model)
