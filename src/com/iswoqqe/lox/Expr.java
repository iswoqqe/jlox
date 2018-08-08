package com.iswoqqe.lox;

abstract class Expr {
    interface Visitor<T> {
        T visitBinaryExpr(Binary binary);
        T visitGroupingExpr(Grouping grouping);
        T visitLiteralExpr(Literal literal);
        T visitTenaryExpr(Tenary tenary);
        T visitUnaryExpr(Unary unary);
    }

    abstract <T> T accept(Visitor<T> visitor);

    static class Binary extends Expr {
        final Expr left;
        final Token operator;
        final Expr right;

        Binary(Expr left, Token operator, Expr right) {
            this.left = left;
            this.operator = operator;
            this.right = right;
        }

        @Override
        <T> T accept(Visitor<T> visitor) {
            return visitor.visitBinaryExpr(this);
        }
    }

    static class Grouping extends Expr {
        final Expr expression;

        Grouping(Expr expression) {
            this.expression = expression;
        }

        @Override
        <T> T accept(Visitor<T> visitor) {
            return visitor.visitGroupingExpr(this);
        }
    }

    static class Literal extends Expr {
        final Object value;

        Literal(Object value) {
            this.value = value;
        }

        @Override
        <T> T accept(Visitor<T> visitor) {
            return visitor.visitLiteralExpr(this);
        }
    }

    static class Tenary extends Expr {
        final Expr condition;
        final Expr trueBranch;
        final Expr falseBranch;

        Tenary(Expr condition, Expr trueBranch, Expr falseBranch) {
            this.condition = condition;
            this.trueBranch = trueBranch;
            this.falseBranch = falseBranch;
        }

        @Override
        <T> T accept(Visitor<T> visitor) {
            return visitor.visitTenaryExpr(this);
        }
    }

    static class Unary extends Expr {
        final Token operator;
        final Expr right;

        Unary(Token operator, Expr right) {
            this.operator = operator;
            this.right = right;
        }

        @Override
        <T> T accept(Visitor<T> visitor) {
            return visitor.visitUnaryExpr(this);
        }
    }
}
