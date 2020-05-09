/*
 * Copyright (c) 2014 Villu Ruusmann
 *
 * This file is part of JPMML-Evaluator
 *
 * JPMML-Evaluator is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JPMML-Evaluator is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with JPMML-Evaluator.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.jpmml.evaluator.mining;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.dmg.pmml.FieldName;
import org.jpmml.evaluator.Computable;
import org.jpmml.evaluator.Evaluator;
import org.jpmml.evaluator.EvaluatorUtil;
import org.jpmml.evaluator.HasEntityId;
import org.jpmml.evaluator.ModelEvaluatorTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SelectAllTest extends ModelEvaluatorTest {

	@Test
	public void evaluate() throws Exception {
		Evaluator evaluator = createModelEvaluator();

		Map<FieldName, ?> arguments = createArguments("sepal_length", 5.1d, "sepal_width", 3.5d, "petal_length", 1.4d, "petal_width", 0.2d);

		Map<FieldName, ?> results = evaluator.evaluate(arguments);

		assertEquals(1, results.size());

		Collection<?> species = (Collection<?>)results.get(FieldName.create("species"));

		assertEquals(5, species.size());

		for(Object value : species){
			assertTrue((value instanceof Computable) & (value instanceof HasEntityId));
		}

		assertEquals(Arrays.asList("setosa", "setosa", "setosa", "setosa", "versicolor"), EvaluatorUtil.decode(species));
	}
}
