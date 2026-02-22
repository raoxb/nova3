package androidx.constraintlayout.core.state;

import androidx.constraintlayout.core.motion.CustomVariable;
import androidx.constraintlayout.core.motion.utils.TypedBundle;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.core.parser.CLArray;
import androidx.constraintlayout.core.parser.CLContainer;
import androidx.constraintlayout.core.parser.CLElement;
import androidx.constraintlayout.core.parser.CLKey;
import androidx.constraintlayout.core.parser.CLNumber;
import androidx.constraintlayout.core.parser.CLObject;
import androidx.constraintlayout.core.parser.CLParsingException;
import androidx.constraintlayout.core.state.Transition;
import java.lang.reflect.Array;

/* loaded from: classes.dex */
public class TransitionParser {
    @Deprecated
    public static void parse(CLObject cLObject, Transition transition, CorePixelDp corePixelDp) throws CLParsingException {
        parse(cLObject, transition);
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x00a2, code lost:
    
        if (r2 != false) goto L49;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void parse(androidx.constraintlayout.core.parser.CLObject r10, androidx.constraintlayout.core.state.Transition r11) throws androidx.constraintlayout.core.parser.CLParsingException {
        /*
            r11.resetProperties()
            java.lang.String r0 = "pathMotionArc"
            java.lang.String r0 = r10.getStringOrNull(r0)
            androidx.constraintlayout.core.motion.utils.TypedBundle r1 = new androidx.constraintlayout.core.motion.utils.TypedBundle
            r1.<init>()
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L81
            r0.hashCode()
            int r4 = r0.hashCode()
            r5 = 5
            r6 = 4
            r7 = 3
            r8 = 2
            r9 = -1
            switch(r4) {
                case -1857024520: goto L59;
                case -1007052250: goto L4e;
                case 3145837: goto L43;
                case 3387192: goto L38;
                case 92611485: goto L2d;
                case 93621297: goto L22;
                default: goto L21;
            }
        L21:
            goto L63
        L22:
            java.lang.String r4 = "below"
            boolean r0 = r0.equals(r4)
            if (r0 != 0) goto L2b
            goto L63
        L2b:
            r9 = r5
            goto L63
        L2d:
            java.lang.String r4 = "above"
            boolean r0 = r0.equals(r4)
            if (r0 != 0) goto L36
            goto L63
        L36:
            r9 = r6
            goto L63
        L38:
            java.lang.String r4 = "none"
            boolean r0 = r0.equals(r4)
            if (r0 != 0) goto L41
            goto L63
        L41:
            r9 = r7
            goto L63
        L43:
            java.lang.String r4 = "flip"
            boolean r0 = r0.equals(r4)
            if (r0 != 0) goto L4c
            goto L63
        L4c:
            r9 = r8
            goto L63
        L4e:
            java.lang.String r4 = "startHorizontal"
            boolean r0 = r0.equals(r4)
            if (r0 != 0) goto L57
            goto L63
        L57:
            r9 = r2
            goto L63
        L59:
            java.lang.String r4 = "startVertical"
            boolean r0 = r0.equals(r4)
            if (r0 != 0) goto L62
            goto L63
        L62:
            r9 = r3
        L63:
            r0 = 509(0x1fd, float:7.13E-43)
            switch(r9) {
                case 0: goto L7d;
                case 1: goto L79;
                case 2: goto L75;
                case 3: goto L71;
                case 4: goto L6d;
                case 5: goto L69;
                default: goto L68;
            }
        L68:
            goto L80
        L69:
            r1.add(r0, r6)
            goto L80
        L6d:
            r1.add(r0, r5)
            goto L80
        L71:
            r1.add(r0, r3)
            goto L80
        L75:
            r1.add(r0, r7)
            goto L80
        L79:
            r1.add(r0, r8)
            goto L80
        L7d:
            r1.add(r0, r2)
        L80:
            r3 = r2
        L81:
            java.lang.String r0 = "interpolator"
            java.lang.String r0 = r10.getStringOrNull(r0)
            if (r0 == 0) goto L8f
            r3 = 705(0x2c1, float:9.88E-43)
            r1.add(r3, r0)
            goto L90
        L8f:
            r2 = r3
        L90:
            java.lang.String r0 = "staggered"
            float r0 = r10.getFloatOrNaN(r0)
            boolean r3 = java.lang.Float.isNaN(r0)
            if (r3 != 0) goto La2
            r2 = 706(0x2c2, float:9.9E-43)
            r1.add(r2, r0)
            goto La4
        La2:
            if (r2 == 0) goto La7
        La4:
            r11.setTransitionProperties(r1)
        La7:
            java.lang.String r0 = "onSwipe"
            androidx.constraintlayout.core.parser.CLObject r0 = r10.getObjectOrNull(r0)
            if (r0 == 0) goto Lb2
            parseOnSwipe(r0, r11)
        Lb2:
            parseKeyFrames(r10, r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.state.TransitionParser.parse(androidx.constraintlayout.core.parser.CLObject, androidx.constraintlayout.core.state.Transition):void");
    }

    private static void parseOnSwipe(CLContainer cLContainer, Transition transition) {
        String stringOrNull = cLContainer.getStringOrNull("anchor");
        int map = map(cLContainer.getStringOrNull("side"), Transition.OnSwipe.SIDES);
        int map2 = map(cLContainer.getStringOrNull("direction"), Transition.OnSwipe.DIRECTIONS);
        float floatOrNaN = cLContainer.getFloatOrNaN("scale");
        float floatOrNaN2 = cLContainer.getFloatOrNaN("threshold");
        float floatOrNaN3 = cLContainer.getFloatOrNaN("maxVelocity");
        float floatOrNaN4 = cLContainer.getFloatOrNaN("maxAccel");
        String stringOrNull2 = cLContainer.getStringOrNull("limitBounds");
        int map3 = map(cLContainer.getStringOrNull("mode"), Transition.OnSwipe.MODE);
        int map4 = map(cLContainer.getStringOrNull("touchUp"), Transition.OnSwipe.TOUCH_UP);
        float floatOrNaN5 = cLContainer.getFloatOrNaN("springMass");
        float floatOrNaN6 = cLContainer.getFloatOrNaN("springStiffness");
        float floatOrNaN7 = cLContainer.getFloatOrNaN("springDamping");
        float floatOrNaN8 = cLContainer.getFloatOrNaN("stopThreshold");
        int map5 = map(cLContainer.getStringOrNull("springBoundary"), Transition.OnSwipe.BOUNDARY);
        String stringOrNull3 = cLContainer.getStringOrNull("around");
        Transition.OnSwipe createOnSwipe = transition.createOnSwipe();
        createOnSwipe.setAnchorId(stringOrNull);
        createOnSwipe.setAnchorSide(map);
        createOnSwipe.setDragDirection(map2);
        createOnSwipe.setDragScale(floatOrNaN);
        createOnSwipe.setDragThreshold(floatOrNaN2);
        createOnSwipe.setMaxVelocity(floatOrNaN3);
        createOnSwipe.setMaxAcceleration(floatOrNaN4);
        createOnSwipe.setLimitBoundsTo(stringOrNull2);
        createOnSwipe.setAutoCompleteMode(map3);
        createOnSwipe.setOnTouchUp(map4);
        createOnSwipe.setSpringMass(floatOrNaN5);
        createOnSwipe.setSpringStiffness(floatOrNaN6);
        createOnSwipe.setSpringDamping(floatOrNaN7);
        createOnSwipe.setSpringStopThreshold(floatOrNaN8);
        createOnSwipe.setSpringBoundary(map5);
        createOnSwipe.setRotationCenterId(stringOrNull3);
    }

    private static int map(String str, String... strArr) {
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i].equals(str)) {
                return i;
            }
        }
        return 0;
    }

    private static void map(TypedBundle typedBundle, int i, String str, String... strArr) {
        for (int i2 = 0; i2 < strArr.length; i2++) {
            if (strArr[i2].equals(str)) {
                typedBundle.add(i, i2);
            }
        }
    }

    public static void parseKeyFrames(CLObject cLObject, Transition transition) throws CLParsingException {
        CLObject objectOrNull = cLObject.getObjectOrNull("KeyFrames");
        if (objectOrNull == null) {
            return;
        }
        CLArray arrayOrNull = objectOrNull.getArrayOrNull("KeyPositions");
        if (arrayOrNull != null) {
            for (int i = 0; i < arrayOrNull.size(); i++) {
                CLElement cLElement = arrayOrNull.get(i);
                if (cLElement instanceof CLObject) {
                    parseKeyPosition((CLObject) cLElement, transition);
                }
            }
        }
        CLArray arrayOrNull2 = objectOrNull.getArrayOrNull(TypedValues.AttributesType.NAME);
        if (arrayOrNull2 != null) {
            for (int i2 = 0; i2 < arrayOrNull2.size(); i2++) {
                CLElement cLElement2 = arrayOrNull2.get(i2);
                if (cLElement2 instanceof CLObject) {
                    parseKeyAttribute((CLObject) cLElement2, transition);
                }
            }
        }
        CLArray arrayOrNull3 = objectOrNull.getArrayOrNull("KeyCycles");
        if (arrayOrNull3 != null) {
            for (int i3 = 0; i3 < arrayOrNull3.size(); i3++) {
                CLElement cLElement3 = arrayOrNull3.get(i3);
                if (cLElement3 instanceof CLObject) {
                    parseKeyCycle((CLObject) cLElement3, transition);
                }
            }
        }
    }

    private static void parseKeyPosition(CLObject cLObject, Transition transition) throws CLParsingException {
        TypedBundle typedBundle = new TypedBundle();
        CLArray array = cLObject.getArray(TypedValues.AttributesType.S_TARGET);
        CLArray array2 = cLObject.getArray("frames");
        CLArray arrayOrNull = cLObject.getArrayOrNull("percentX");
        CLArray arrayOrNull2 = cLObject.getArrayOrNull("percentY");
        CLArray arrayOrNull3 = cLObject.getArrayOrNull("percentWidth");
        CLArray arrayOrNull4 = cLObject.getArrayOrNull("percentHeight");
        String stringOrNull = cLObject.getStringOrNull(TypedValues.TransitionType.S_PATH_MOTION_ARC);
        String stringOrNull2 = cLObject.getStringOrNull("transitionEasing");
        String stringOrNull3 = cLObject.getStringOrNull("curveFit");
        String stringOrNull4 = cLObject.getStringOrNull("type");
        if (stringOrNull4 == null) {
            stringOrNull4 = "parentRelative";
        }
        if (arrayOrNull == null || array2.size() == arrayOrNull.size()) {
            if (arrayOrNull2 == null || array2.size() == arrayOrNull2.size()) {
                int i = 0;
                while (i < array.size()) {
                    String string = array.getString(i);
                    int map = map(stringOrNull4, "deltaRelative", "pathRelative", "parentRelative");
                    typedBundle.clear();
                    typedBundle.add(TypedValues.PositionType.TYPE_POSITION_TYPE, map);
                    if (stringOrNull3 != null) {
                        map(typedBundle, TypedValues.PositionType.TYPE_CURVE_FIT, stringOrNull3, "spline", "linear");
                    }
                    typedBundle.addIfNotNull(TypedValues.PositionType.TYPE_TRANSITION_EASING, stringOrNull2);
                    if (stringOrNull != null) {
                        map(typedBundle, 509, stringOrNull, "none", "startVertical", "startHorizontal", "flip", "below", "above");
                    }
                    int i2 = 0;
                    while (i2 < array2.size()) {
                        typedBundle.add(100, array2.getInt(i2));
                        set(typedBundle, TypedValues.PositionType.TYPE_PERCENT_X, arrayOrNull, i2);
                        set(typedBundle, TypedValues.PositionType.TYPE_PERCENT_Y, arrayOrNull2, i2);
                        set(typedBundle, TypedValues.PositionType.TYPE_PERCENT_WIDTH, arrayOrNull3, i2);
                        set(typedBundle, TypedValues.PositionType.TYPE_PERCENT_HEIGHT, arrayOrNull4, i2);
                        transition.addKeyPosition(string, typedBundle);
                        i2++;
                        stringOrNull4 = stringOrNull4;
                    }
                    i++;
                    stringOrNull4 = stringOrNull4;
                }
            }
        }
    }

    private static void set(TypedBundle typedBundle, int i, CLArray cLArray, int i2) throws CLParsingException {
        if (cLArray != null) {
            typedBundle.add(i, cLArray.getFloat(i2));
        }
    }

    private static void parseKeyAttribute(CLObject cLObject, Transition transition) throws CLParsingException {
        CLArray arrayOrNull;
        CustomVariable[][] customVariableArr;
        CLObject cLObject2;
        int i;
        int i2;
        String[] strArr;
        CLArray arrayOrNull2 = cLObject.getArrayOrNull(TypedValues.AttributesType.S_TARGET);
        if (arrayOrNull2 == null || (arrayOrNull = cLObject.getArrayOrNull("frames")) == null) {
            return;
        }
        String stringOrNull = cLObject.getStringOrNull("transitionEasing");
        String[] strArr2 = {"scaleX", "scaleY", "translationX", "translationY", "translationZ", "rotationX", "rotationY", "rotationZ", "alpha"};
        int[] iArr = {311, 312, 304, 305, 306, 308, 309, 310, 303};
        boolean[] zArr = {false, false, true, true, true, false, false, false, false};
        int size = arrayOrNull.size();
        TypedBundle[] typedBundleArr = new TypedBundle[size];
        for (int i3 = 0; i3 < arrayOrNull.size(); i3++) {
            typedBundleArr[i3] = new TypedBundle();
        }
        int i4 = 0;
        for (int i5 = 9; i4 < i5; i5 = 9) {
            String str = strArr2[i4];
            int i6 = iArr[i4];
            boolean z = zArr[i4];
            CLArray arrayOrNull3 = cLObject.getArrayOrNull(str);
            if (arrayOrNull3 != null && arrayOrNull3.size() != size) {
                throw new CLParsingException("incorrect size for " + str + " array, not matching targets array!", cLObject);
            }
            if (arrayOrNull3 != null) {
                int i7 = 0;
                while (i7 < size) {
                    float f = arrayOrNull3.getFloat(i7);
                    String[] strArr3 = strArr2;
                    if (z) {
                        f = transition.mToPixel.toPixels(f);
                    }
                    typedBundleArr[i7].add(i6, f);
                    i7++;
                    strArr2 = strArr3;
                }
                strArr = strArr2;
            } else {
                strArr = strArr2;
                float floatOrNaN = cLObject.getFloatOrNaN(str);
                if (!Float.isNaN(floatOrNaN)) {
                    if (z) {
                        floatOrNaN = transition.mToPixel.toPixels(floatOrNaN);
                    }
                    for (int i8 = 0; i8 < size; i8++) {
                        typedBundleArr[i8].add(i6, floatOrNaN);
                    }
                }
            }
            i4++;
            strArr2 = strArr;
        }
        CLElement orNull = cLObject.getOrNull("custom");
        if (orNull == null || !(orNull instanceof CLObject)) {
            customVariableArr = null;
        } else {
            CLObject cLObject3 = (CLObject) orNull;
            int size2 = cLObject3.size();
            customVariableArr = (CustomVariable[][]) Array.newInstance((Class<?>) CustomVariable.class, arrayOrNull.size(), size2);
            int i9 = 0;
            while (i9 < size2) {
                CLKey cLKey = (CLKey) cLObject3.get(i9);
                String content = cLKey.content();
                if (cLKey.getValue() instanceof CLArray) {
                    CLArray cLArray = (CLArray) cLKey.getValue();
                    int size3 = cLArray.size();
                    if (size3 == size && size3 > 0) {
                        if (cLArray.get(0) instanceof CLNumber) {
                            int i10 = 0;
                            while (i10 < size) {
                                customVariableArr[i10][i9] = new CustomVariable(content, TypedValues.Custom.TYPE_FLOAT, cLArray.get(i10).getFloat());
                                i10++;
                                cLObject3 = cLObject3;
                            }
                        } else {
                            cLObject2 = cLObject3;
                            int i11 = 0;
                            while (i11 < size) {
                                long parseColorString = ConstraintSetParser.parseColorString(cLArray.get(i11).content());
                                if (parseColorString != -1) {
                                    i2 = size2;
                                    customVariableArr[i11][i9] = new CustomVariable(content, TypedValues.Custom.TYPE_COLOR, (int) parseColorString);
                                } else {
                                    i2 = size2;
                                }
                                i11++;
                                size2 = i2;
                            }
                            i = size2;
                        }
                    }
                    cLObject2 = cLObject3;
                    i = size2;
                } else {
                    cLObject2 = cLObject3;
                    i = size2;
                    CLElement value = cLKey.getValue();
                    if (value instanceof CLNumber) {
                        float f2 = value.getFloat();
                        for (int i12 = 0; i12 < size; i12++) {
                            customVariableArr[i12][i9] = new CustomVariable(content, TypedValues.Custom.TYPE_FLOAT, f2);
                        }
                    } else {
                        long parseColorString2 = ConstraintSetParser.parseColorString(value.content());
                        if (parseColorString2 != -1) {
                            int i13 = 0;
                            while (i13 < size) {
                                customVariableArr[i13][i9] = new CustomVariable(content, TypedValues.Custom.TYPE_COLOR, (int) parseColorString2);
                                i13++;
                                parseColorString2 = parseColorString2;
                            }
                        }
                    }
                }
                i9++;
                cLObject3 = cLObject2;
                size2 = i;
            }
        }
        String stringOrNull2 = cLObject.getStringOrNull("curveFit");
        for (int i14 = 0; i14 < arrayOrNull2.size(); i14++) {
            for (int i15 = 0; i15 < size; i15++) {
                String string = arrayOrNull2.getString(i14);
                TypedBundle typedBundle = typedBundleArr[i15];
                if (stringOrNull2 != null) {
                    typedBundle.add(TypedValues.PositionType.TYPE_CURVE_FIT, map(stringOrNull2, "spline", "linear"));
                }
                typedBundle.addIfNotNull(TypedValues.PositionType.TYPE_TRANSITION_EASING, stringOrNull);
                typedBundle.add(100, arrayOrNull.getInt(i15));
                transition.addKeyAttribute(string, typedBundle, customVariableArr != null ? customVariableArr[i15] : null);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:77:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x014f A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void parseKeyCycle(androidx.constraintlayout.core.parser.CLObject r19, androidx.constraintlayout.core.state.Transition r20) throws androidx.constraintlayout.core.parser.CLParsingException {
        /*
            Method dump skipped, instructions count: 410
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.state.TransitionParser.parseKeyCycle(androidx.constraintlayout.core.parser.CLObject, androidx.constraintlayout.core.state.Transition):void");
    }
}
