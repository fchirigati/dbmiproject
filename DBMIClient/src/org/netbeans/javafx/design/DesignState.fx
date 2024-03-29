/*
 * Copyright (c) 2009, Sun Microsystems, Inc.
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 * 
 *  * Redistributions of source code must retain the above copyright notice, 
 *    this list of conditions and the following disclaimer.
 *  * Redistributions in binary form must reproduce the above copyright 
 *    notice, this list of conditions and the following disclaimer in 
 *    the documentation and/or other materials provided with the distribution.
 *  * Neither the name of Sun Microsystems, Inc. nor the names of its 
 *    contributors may be used to endorse or promote products derived 
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT 
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED 
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
 */
 
package org.netbeans.javafx.design;

import javafx.animation.Timeline;
import org.netbeans.javafx.design.DesignStateChangeType;

/**
 * Represents a container of a single state variable in a design.
 * State variable can hold at maximum one particular state at a time.
 * The actual state is held in <code>actual</code> field.
 *
 * It is required to set <code>timelines</code> field.
 * Then you can switch between states using <code>actual</code> field directly or any helper method e.g. <code>previous</code> or <code>next</code>.
 * Optionally you can set <code>names</code> field to navigate to states using their names.
 */
public class DesignState {

    /**
     * Contains a sequence of names.
     * Each name is assigned to a particular state represented by an index starting from zero.
     * For getting an index from a specified name, you can use <code>findIndex</code> method.
     */
    public-init var names: String[];

    /**
     * Contains a sequence of timelines.
     * Each timeline is assigned to a particular state represented by an index starting from zero.
     * A timeline sets various properties of components in a design to specific value.
     * At the end of the timeline, the design should look as designed for a specific state.
     */
    public-init var timelines: Timeline[];

    /**
     * This function should create a timeline for a specified actual state.
     * Default implementation returns a timeline stored <code>timelines</code> sequence at <code>actual</code> position.
     */
    public-init var createTimeline: function (actual: Integer): Timeline = function (actual) {
        timelines[actual]
    };

    /**
     * Defines how the timelines should be stop/started when the actual state is changed.
     * Default value is <code>PAUSE_AND_PLAY_FROM_START</code>.
     * If null value is set, <code>IllegalArgumentException</code> is thrown.
     */
    public-init var stateChangeType = DesignStateChangeType.PAUSE_AND_PLAY_FROM_START on replace oldValue {
        if (stateChangeType == null) {
            stateChangeType = oldValue;
            throw new java.lang.IllegalArgumentException ();
        }
    }

    /**
     * This is a notification method called when the actual state is changed.
     * @param oldState the previous state
     * @param newState the new actual state
     */
    public var onActualStateChanged: function (oldState: Integer,newState: Integer): Void;

    var actualTimeline: Timeline;

    /**
     * Holds the actual state index. The defined indices are [0 .. < sizeof names].
     * Any other index is taken as undefined.
     * Default actual state index is <code>-1</code>.
     * When a new actual state index is set, then based on <code>onActualStateChanged</code>
     * a new timeline is started and <code>onActualStateChanged</code> method is called.
     */
    public var actual = -1 on replace old {
        if (stateChangeType == DesignStateChangeType.PAUSE_AND_PLAY_FROM_START) {
            actualTimeline.stop ();
            actualTimeline = createTimeline (actual);
            actualTimeline.playFromStart ();
        } else if (stateChangeType == DesignStateChangeType.FINISH_AND_PLAY_FROM_START) {
            actualTimeline.time = actualTimeline.totalDuration;
            actualTimeline = createTimeline (actual);
            actualTimeline.playFromStart ();
        } else if (stateChangeType == DesignStateChangeType.CONTINUE_AND_PLAY) {
            actualTimeline.play ();
        } else if (stateChangeType == DesignStateChangeType.DO_NOTHING) {
        }

        onActualStateChanged (old, actual);
    }

    /**
     * Returns a state index by a state name which can be found in <code>names</code> field.
     * @param name the state name to search for
     * @return the state index or <code>-1</code> if not found.
     */
    public function findIndex (name: String): Integer {
        javafx.util.Sequences.indexOf(names, name);
    }

    /**
     * If possible, sets the actual state index to previous state index
     * i.e. decrements the actual state index by 1.
     * The function cares about the lower limit and disables decrement
     * when the new actual state index would be undefined index.
     */
    public function previous () {
        if (actual > 0) {
            actual = actual - 1;
        }
    }

    /**
     * If possible, sets the actual state index to next state index
     * i.e. increments the actual state index by 1.
     * The function cares about the upper limit and disables increment
     * when the new actual state index would be undefined index.
     */
    public function next () {
        if (actual < sizeof names - 1) {
            actual = actual + 1;
        }
    }

    /**
     * If possible, sets the actual state index to previous state index in a loop
     * i.e. decrements the actual state index by 1 in a loop of defined indices.
     * If the new actual state index would be undefined index, the last defined index is set instead.
     */
    public function previousWrapped () {
        actual = if (actual > 0) then actual - 1 else sizeof names - 1
    }

    /**
     * If possible, sets the actual state index to next state index in a loop
     * i.e. increments the actual state index by 1 in a loop of defined indices.
     * If the new actual state index would be undefined index, the first defined index is set instead.
     */
    public function nextWrapped () {
        actual = if (actual < sizeof names - 1) then actual + 1 else 0
    }

}
