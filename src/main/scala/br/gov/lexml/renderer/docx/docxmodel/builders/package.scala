package br.gov.lexml.renderer.docx.docxmodel

import cats.data.*

package object builders:
  type RunBuilderMonad[T,A] = State[RunBuilderState[T],A]

  type RunBuilderMonadStmt[T] = RunBuilderMonad[T,Unit]

  type ParBuilderMonad[T,A] = State[ParBuilderState[T],A]

  type ParBuilderMonadStmt[T] = ParBuilderMonad[T,Unit]

  type DocxCompSeqBuilderMonad[T,A] = State[DocxCompSeqBuilderState[T],A]

  type DocxCompSeqBuilderMonadStmt[T] = DocxCompSeqBuilderMonad[T,Unit]

   def mapM_[T,S](x : Seq[T])(f : T => State[S,_]) : State[S,Unit] =
        if x.isEmpty then
          State.pure(())
        else
          val (h,t) = (x.head,x.tail)
          for {
            _ <- f(h)
            _ <- mapM_(t)(f)
          } yield ()

  type Mergeable[T] = Mergeable2[T,T]
end builders