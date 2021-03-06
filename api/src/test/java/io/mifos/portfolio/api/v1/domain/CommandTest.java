/*
 * Copyright 2017 The Mifos Initiative.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.mifos.portfolio.api.v1.domain;

import io.mifos.core.test.domain.ValidationTest;
import io.mifos.core.test.domain.ValidationTestCase;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static io.mifos.individuallending.api.v1.domain.product.ChargeIdentifiers.PROCESSING_FEE_ID;

/**
 * @author Myrle Krantz
 */
@RunWith(Parameterized.class)
public class CommandTest extends ValidationTest<Command> {
  public CommandTest(ValidationTestCase<Command> testCase) {
    super(testCase);
  }

  @Override
  protected Command createValidTestSubject() {
    final Command ret = new Command();
    ret.setOneTimeAccountAssignments(Collections.emptyList());
    return ret;
  }

  @Parameterized.Parameters
  public static Collection testCases() {
    final Collection<ValidationTestCase> ret = new ArrayList<>();
    ret.add(new ValidationTestCase<Command>("valid"));
    ret.add(new ValidationTestCase<Command>("invalidAccountAssignment")
            .adjustment(x -> x.setOneTimeAccountAssignments(Collections.singletonList(new AccountAssignment("", ""))))
            .valid(false));
    ret.add(new ValidationTestCase<Command>("validAccountAssignment")
            .adjustment(x -> x.setOneTimeAccountAssignments(Collections.singletonList(new AccountAssignment(PROCESSING_FEE_ID, "7534"))))
            .valid(true));
    ret.add(new ValidationTestCase<Command>("nullAccountAssignment")
            .adjustment(x -> x.setOneTimeAccountAssignments(null))
            .valid(true));
    return ret;
  }
}
