/*
 * Copyright (c) 2010, 2012, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package jdk.nashorn.internal.objects;

import static jdk.nashorn.internal.runtime.ScriptRuntime.UNDEFINED;

import java.io.PrintStream;
import java.util.Objects;
import jdk.nashorn.internal.objects.annotations.Attribute;
import jdk.nashorn.internal.objects.annotations.Function;
import jdk.nashorn.internal.objects.annotations.ScriptClass;
import jdk.nashorn.internal.objects.annotations.Where;
import jdk.nashorn.internal.runtime.PropertyListenerManager;
import jdk.nashorn.internal.runtime.PropertyMap;
import jdk.nashorn.internal.runtime.ScriptFunction;
import jdk.nashorn.internal.runtime.ScriptObject;
import jdk.nashorn.internal.runtime.linker.LinkerCallSite;

/**
 * Nashorn specific debug utils. This is meant for Nashorn developers.
 * The interface is subject to change without notice!!
 *
 */
@ScriptClass("Debug")
public class NativeDebug extends ScriptObject {
    NativeDebug() {
        this.setProto(Global.objectPrototype());
    }

    @Override
    public String getClassName() {
        return "Debug";
    }

    /**
     * Nashorn extension: get context, context utility
     *
     * @param self self reference
     * @return context
     */
    @Function(attributes = Attribute.NOT_ENUMERABLE, where = Where.CONSTRUCTOR)
    public static Object getContext(final Object self) {
        return Global.getThisContext();
    }

    /**
     * Nashorn extension: get map from {@link ScriptObject}
     *
     * @param self self reference
     * @param obj script object
     * @return the map for the current ScriptObject
     */
    @Function(attributes = Attribute.NOT_ENUMERABLE, where = Where.CONSTRUCTOR)
    public static Object map(final Object self, final Object obj) {
        if (obj instanceof ScriptObject) {
            return ((ScriptObject)obj).getMap();
        }
        return UNDEFINED;
    }

    /**
     * Nashorn extension: get embed0 from {@link ScriptObject}
     *
     * @param self self reference
     * @param obj script object
     * @return the embed0 property value for the given ScriptObject
     */
    @Function(attributes = Attribute.NOT_ENUMERABLE, where = Where.CONSTRUCTOR)
    public static Object embed0(final Object self, final Object obj) {
        if (obj instanceof ScriptObject) {
            return ((ScriptObject)obj).embed0;
        }
        return UNDEFINED;
    }

    /**
     * Nashorn extension: get embed1 from {@link ScriptObject}
     *
     * @param self self reference
     * @param obj script object
     * @return the embed1 property value for the given ScriptObject
     */
    @Function(attributes = Attribute.NOT_ENUMERABLE, where = Where.CONSTRUCTOR)
    public static Object embed1(final Object self, final Object obj) {
        if (obj instanceof ScriptObject) {
            return ((ScriptObject)obj).embed1;
        }
        return UNDEFINED;
    }

    /**
     * Nashorn extension: get embed2 from {@link ScriptObject}
     *
     * @param self self reference
     * @param obj script object
     * @return the embed2 property value for the given ScriptObject
     */
    @Function(attributes = Attribute.NOT_ENUMERABLE, where = Where.CONSTRUCTOR)
    public static Object embed2(final Object self, final Object obj) {
        if (obj instanceof ScriptObject) {
            return ((ScriptObject)obj).embed2;
        }
        return UNDEFINED;
    }

    /**
     * Nashorn extension: get embed3 from {@link ScriptObject}
     *
     * @param self self reference
     * @param obj script object
     * @return the embed3 property value for the given ScriptObject
     */
    @Function(attributes = Attribute.NOT_ENUMERABLE, where = Where.CONSTRUCTOR)
    public static Object embed3(final Object self, final Object obj) {
        if (obj instanceof ScriptObject) {
            return ((ScriptObject)obj).embed3;
        }
        return UNDEFINED;
    }

    /**
     * Nashorn extension: get spill vector from {@link ScriptObject}
     *
     * @param self self reference
     * @param obj script object
     * @return the spill vector for the given ScriptObject
     */
    @Function(attributes = Attribute.NOT_ENUMERABLE, where = Where.CONSTRUCTOR)
    public static Object spill(final Object self, final Object obj) {
        if (obj instanceof ScriptObject) {
            return ((ScriptObject)obj).spill;
        }
        return UNDEFINED;
    }

    /**
     * Nashorn extension: get invocation handle from {@link ScriptFunction}
     *
     * @param self self reference
     * @param obj script function
     * @return the invocation handle for the given ScriptFunction
     */
    @Function(attributes = Attribute.NOT_ENUMERABLE, where = Where.CONSTRUCTOR)
    public static Object methodHandle(final Object self, final Object obj) {
        if (obj instanceof ScriptFunction) {
            return ((ScriptFunction)obj).getInvokeHandle();
        }
        return UNDEFINED;
    }

    /**
     * Check object identity comparison regardless of type
     *
     * @param self self reference
     * @param obj1 first object in comparison
     * @param obj2 second object in comparison
     * @return true if reference identity
     */
    @Function(attributes = Attribute.NOT_ENUMERABLE, where = Where.CONSTRUCTOR)
    public static Object identical(final Object self, final Object obj1, final Object obj2) {
        return obj1 == obj2;
    }

    /**
     * Object util - getClass
     *
     * @param self self reference
     * @param obj  object
     * @return class of {@code obj}
     */
    @Function(attributes = Attribute.NOT_ENUMERABLE, where = Where.CONSTRUCTOR)
    public static Object getClass(final Object self, final Object obj) {
        if (obj != null) {
            return obj.getClass();
        }
        return UNDEFINED;
    }

    /**
     * Object util - equals
     *
     * @param self self reference
     * @param obj1 first object in comparison
     * @param obj2 second object in comparison
     * @return return {@link Object#equals(Object)} for objects.
     */
    @Function(attributes = Attribute.NOT_ENUMERABLE, where = Where.CONSTRUCTOR)
    public static Object equals(final Object self, final Object obj1, final Object obj2) {
        return (obj1 != null) ? obj1.equals(obj2) : false;
    }

    /**
     * Object util - toJavaString
     *
     * @param self self reference
     * @param obj  object to represent as a string
     * @return Java string representation of {@code obj}
     */
    @Function(attributes = Attribute.NOT_ENUMERABLE, where = Where.CONSTRUCTOR)
    public static Object toJavaString(final Object self, final Object obj) {
        return Objects.toString(obj);
    }

    /**
     * Do not call overridden toString -- use default toString impl
     *
     * @param self self reference
     * @param obj  object to represent as a string
     * @return string representation
     */
    @Function(attributes = Attribute.NOT_ENUMERABLE, where = Where.CONSTRUCTOR)
    public static Object toIdentString(final Object self, final Object obj) {
        if (obj == null) {
            return "null";
        }

        final int hash = System.identityHashCode(obj);
        return obj.getClass() + "@" + Integer.toHexString(hash);
    }

    /**
     * Dump all Nashorn debug mode counters. Calling this may be better if
     * you want to print all counters. This way you can avoid too many callsites
     * due to counter access itself!!
     * @param self self reference
     * @return undefined
     */
    @Function(attributes = Attribute.NOT_ENUMERABLE, where = Where.CONSTRUCTOR)
    public static Object dumpCounters(final Object self) {
        final PrintStream out = System.err;
        out.println("ScriptObject count " + ScriptObject.getCount());
        out.println("Scope count " + ScriptObject.getScopeCount());
        out.println("ScriptObject listeners added " + PropertyListenerManager.getListenersAdded());
        out.println("ScriptObject listeners removed " + PropertyListenerManager.getListenersRemoved());
        out.println("ScriptObject listeners dead " + PropertyListenerManager.getListenersDead());
        out.println("ScriptFunction count " + ScriptObject.getCount());
        out.println("ScriptFunction invokes " + ScriptFunction.getInvokes());
        out.println("ScriptFunction allocations " + ScriptFunction.getAllocations());
        out.println("PropertyMap count " + PropertyMap.getCount());
        out.println("PropertyMap cloned " + PropertyMap.getClonedCount());
        out.println("PropertyMap history hit " + PropertyMap.getHistoryHit());
        out.println("PropertyMap proto invalidations " + PropertyMap.getProtoInvalidations());
        out.println("PropertyMap proto history hit " + PropertyMap.getProtoHistoryHit());
        out.println("PropertyMap setProtoNewMapCount " + PropertyMap.getSetProtoNewMapCount());
        out.println("Callsite count " + LinkerCallSite.getCount());
        out.println("Callsite misses " + LinkerCallSite.getMissCount());
        out.println("Callsite misses by site at " + LinkerCallSite.getMissSamplingPercentage() + "%");
        LinkerCallSite.getMissCounts(out);
        return UNDEFINED;
    }
}