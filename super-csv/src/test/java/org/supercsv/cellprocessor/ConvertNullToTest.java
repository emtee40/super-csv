/*
 * Copyright 2007 Kasper B. Graversen
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.supercsv.cellprocessor;

import static org.junit.Assert.assertEquals;
import static org.supercsv.SuperCsvTestUtils.ANONYMOUS_CSVCONTEXT;

import org.junit.Before;
import org.junit.Test;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.mock.IdentityTransform;

/**
 * Tests the ConvertNull processor.
 * 
 * @author James Bassett
 */
public class ConvertNullToTest {
	
	private static final String CONVERTED_VALUE = "previously null!";
	
	private CellProcessor processor;
	private CellProcessor processorChain;
	
	/**
	 * Sets up the processors for the test using all constructor combinations.
	 */
	@Before
	public void setUp() {
		processor = new ConvertNullTo(CONVERTED_VALUE);
		processorChain = new ConvertNullTo(CONVERTED_VALUE, new IdentityTransform());
	}
	
	/**
	 * Tests unchained/chained execution with a null value (should return converted value).
	 */
	@Test
	public void testWithNullValue() {
		assertEquals(CONVERTED_VALUE, processor.execute(null, ANONYMOUS_CSVCONTEXT));
		assertEquals(CONVERTED_VALUE, processorChain.execute(null, ANONYMOUS_CSVCONTEXT));
	}
	
	/**
	 * Tests unchained/chained execution with a non-null value (should return input unchanged).
	 */
	@Test
	public void testWithNonNullValue() {
		String notNull = "not null!";
		assertEquals(notNull, processor.execute(notNull, ANONYMOUS_CSVCONTEXT));
		assertEquals(notNull, processorChain.execute(notNull, ANONYMOUS_CSVCONTEXT));
	}
	
}
