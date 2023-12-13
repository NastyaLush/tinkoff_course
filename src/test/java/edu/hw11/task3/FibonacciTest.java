package edu.hw11.task3;

import java.lang.reflect.InvocationTargetException;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3 {

    @Test
    public void fib_shouldCalcFibonacci()
        throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<?> dynamicClass = new ByteBuddy().subclass(Object.class)
                                               .name("Fibonacci")
                                               .defineMethod("fib", long.class, Opcodes.ACC_PUBLIC)
                                               .withParameter(long.class)
                                               .intercept(new Implementation.Simple(new FibMethod()))
                                               .make()
                                               .load(getClass().getClassLoader())
                                               .getLoaded();

        Object dynamicInstance = dynamicClass.getDeclaredConstructor().newInstance();
        long input = 3;
        long expectedAnswer = fib(input);
        long actualAnswer = (long) dynamicClass.getMethod("fib", long.class).invoke(dynamicInstance, input);
        assertEquals(expectedAnswer, actualAnswer);
    }

    long fib(long n) {
        if (n <= 0) {return 0;}
        if (n == 1) {return 1;}
        return fib(n - 1) + fib(n - 2);
    }

    enum IntegerSum implements StackManipulation {
        INSTANCE;

        @Override
        public boolean isValid() {
            return true;
        }

        @Override
        public Size apply(MethodVisitor methodVisitor, Implementation.Context implementationContext) {
            Label start = new Label();
            Label labelIfLessOrEqual0 = new Label();
            Label labelIfEqual1 = new Label();
            Label labelIfMoreThan1 = new Label();
            Label finish = new Label();

            methodVisitor.visitCode();

            int indexArg = 1;
            methodVisitor.visitLocalVariable("arg", "J", null, start, finish, indexArg);
            int indexPrevSum = 3;
            methodVisitor.visitLocalVariable("prevSum", "J", null, start, finish, indexPrevSum);
            int indexPrevPrevSum = 5;
            methodVisitor.visitLocalVariable("prevPrevSum", "J", null, start, finish, indexPrevPrevSum);
            int indexAnswerSum = 7;
            methodVisitor.visitLocalVariable("answer", "J", null, start, finish, indexAnswerSum);
            int indexCounter = 9;
            methodVisitor.visitLocalVariable("counter", "J", null, start, finish, indexCounter);

            methodVisitor.visitLabel(start);
            methodVisitor.visitVarInsn(Opcodes.LLOAD, indexArg);
            methodVisitor.visitInsn(Opcodes.LCONST_0);
            methodVisitor.visitInsn(Opcodes.LCMP);
            methodVisitor.visitJumpInsn(Opcodes.IFGT, labelIfLessOrEqual0);
            methodVisitor.visitInsn(Opcodes.LCONST_0);
            methodVisitor.visitInsn(Opcodes.LRETURN);

            methodVisitor.visitLabel(labelIfLessOrEqual0);
            methodVisitor.visitFrame(
                Opcodes.F_SAME,
                0, null,
                0, null
            );
            methodVisitor.visitVarInsn(Opcodes.LLOAD, indexArg);
            methodVisitor.visitInsn(Opcodes.LCONST_1);
            methodVisitor.visitInsn(Opcodes.LCMP);
            methodVisitor.visitJumpInsn(Opcodes.IFNE, labelIfEqual1);
            methodVisitor.visitInsn(Opcodes.LCONST_1);
            methodVisitor.visitInsn(Opcodes.LRETURN);

            methodVisitor.visitLabel(labelIfEqual1);
            methodVisitor.visitFrame(
                Opcodes.F_SAME,
                0, null,
                0, null
            );
            methodVisitor.visitInsn(Opcodes.LCONST_0);
            methodVisitor.visitVarInsn(Opcodes.LSTORE, indexPrevSum);
            methodVisitor.visitInsn(Opcodes.LCONST_1);
            methodVisitor.visitVarInsn(Opcodes.LSTORE, indexPrevPrevSum);
            methodVisitor.visitInsn(Opcodes.LCONST_0);
            methodVisitor.visitVarInsn(Opcodes.LSTORE, indexAnswerSum);
            methodVisitor.visitFrame(
                Opcodes.F_APPEND,
                indexPrevSum, new Object[] {Opcodes.LONG, Opcodes.LONG, Opcodes.LONG},
                0,
                null
            );
            methodVisitor.visitInsn(Opcodes.ICONST_2);
            methodVisitor.visitInsn(Opcodes.I2L);
            methodVisitor.visitVarInsn(Opcodes.LSTORE, indexCounter);

            methodVisitor.visitFrame(
                Opcodes.F_APPEND,
                indexArg, new Object[] {Opcodes.LONG},
                0,
                null
            );
            methodVisitor.visitLabel(labelIfMoreThan1);
            methodVisitor.visitFrame(
                Opcodes.F_SAME,
                0, null,
                0, null
            );
            methodVisitor.visitVarInsn(Opcodes.LLOAD, indexCounter);
            methodVisitor.visitVarInsn(Opcodes.LLOAD, indexArg);
            methodVisitor.visitInsn(Opcodes.LCMP);
            methodVisitor.visitJumpInsn(Opcodes.IFGT, finish);
            methodVisitor.visitVarInsn(Opcodes.LLOAD, indexPrevSum);
            methodVisitor.visitVarInsn(Opcodes.LLOAD, indexPrevPrevSum);
            methodVisitor.visitInsn(Opcodes.LADD);
            methodVisitor.visitVarInsn(Opcodes.LSTORE, indexAnswerSum);
            methodVisitor.visitVarInsn(Opcodes.LLOAD, indexPrevPrevSum);
            methodVisitor.visitVarInsn(Opcodes.LSTORE, indexPrevSum);
            methodVisitor.visitVarInsn(Opcodes.LLOAD, indexAnswerSum);
            methodVisitor.visitVarInsn(Opcodes.LSTORE, indexPrevPrevSum);
            methodVisitor.visitVarInsn(Opcodes.LLOAD, indexCounter);
            methodVisitor.visitInsn(Opcodes.LCONST_1);
            methodVisitor.visitInsn(Opcodes.LADD);
            methodVisitor.visitVarInsn(Opcodes.LSTORE, indexCounter);
            methodVisitor.visitJumpInsn(Opcodes.GOTO, labelIfMoreThan1);

            methodVisitor.visitLabel(finish);
            methodVisitor.visitFrame(
                Opcodes.F_SAME,
                0, null,
                0, null
            );
            methodVisitor.visitVarInsn(Opcodes.LLOAD, indexAnswerSum);
            methodVisitor.visitInsn(Opcodes.LRETURN);

            return new Size(2, 4);
        }
    }

    class FibMethod implements ByteCodeAppender {

        @Override
        public Size apply(
            MethodVisitor methodVisitor,
            Implementation.Context implementationContext,
            MethodDescription instrumentedMethod
        ) {
            if (!instrumentedMethod.getReturnType().asErasure().represents(long.class)) {
                throw new IllegalArgumentException(instrumentedMethod + " must return long");
            }
            StackManipulation.Size operandStackSize = new StackManipulation.Compound(
                IntegerSum.INSTANCE
            ).apply(methodVisitor, implementationContext);

            return new Size(operandStackSize.getMaximalSize(), instrumentedMethod.getStackSize() + 8);
        }
    }
}
