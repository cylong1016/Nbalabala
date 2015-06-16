/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package test.players;

import java.io.Serializable;
import java.util.BitSet;
import java.util.Comparator;
import java.util.List;

public class TwoComparatorChain<E> implements Comparator<E>, Serializable {

    /** Serialization version from Collections 2.0. */
    private static final long serialVersionUID = -721644942746081630L;

    private final Comparator<E> nameComparator;
    private final Comparator<E> otherComparator;

    /**
     * Construct a ComparatorChain from the Comparators in the
     * List.  All Comparators will default to the forward
     * sort order.
     *
     * @param list   List of Comparators
     * @see #ComparatorChain(List,BitSet)
     */
    public TwoComparatorChain(final Comparator<E> nameComparator, final Comparator<E> otherComparator) {
    	this.nameComparator = nameComparator;
    	this.otherComparator = otherComparator;
    }

    public int compare(final E o1, final E o2) throws UnsupportedOperationException {
    	
    	int retval = otherComparator.compare(o1,o2);
    	if (retval != 0) return retval;
    	else return nameComparator.compare(o1, o2);

    }
}
