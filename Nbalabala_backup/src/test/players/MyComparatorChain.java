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
import java.util.Iterator;
import java.util.List;

public class MyComparatorChain<E> implements Comparator<E>, Serializable {

    /** Serialization version from Collections 2.0. */
    private static final long serialVersionUID = -721644942746081630L;

    /** The list of comparators in the chain. */
    private final List<Comparator<E>> comparatorChain;

    /**
     * Construct a ComparatorChain from the Comparators in the
     * List.  All Comparators will default to the forward
     * sort order.
     *
     * @param list   List of Comparators
     * @see #ComparatorChain(List,BitSet)
     */
    public MyComparatorChain(final List<Comparator<E>> list) {
    	this.comparatorChain = list;
    }

    public int compare(final E o1, final E o2) throws UnsupportedOperationException {

        // iterate over all comparators in the chain
        final Iterator<Comparator<E>> comparators = comparatorChain.iterator();
        while(comparators.hasNext()) {
            final Comparator<? super E> comparator = comparators.next();
            int retval = comparator.compare(o1,o2);
            if (retval != 0) {
                return retval;
            }
        }
        // if comparators are exhausted, return 0
        return 0;
    }
}
