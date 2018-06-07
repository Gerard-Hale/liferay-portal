/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.layout.page.template.util;

import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutPrototype;
import com.liferay.portal.kernel.model.LayoutTypePortlet;
import com.liferay.portal.kernel.service.LayoutPrototypeLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Eudaldo Alonso
 */
public class LayoutPrototypeHelper {

	public static Layout addLayoutPrototype(
			long companyId, long defaultUserId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String layoutTemplateId,
			List<LayoutPrototype> layoutPrototypes)
		throws Exception {

		String name = nameMap.get(LocaleUtil.getDefault());

		for (LayoutPrototype layoutPrototype : layoutPrototypes) {
			String curName = layoutPrototype.getName(LocaleUtil.getDefault());

			if ((name == null) || name.equals(curName)) {
				return null;
			}
		}

		LayoutPrototype layoutPrototype =
			LayoutPrototypeLocalServiceUtil.addLayoutPrototype(
				defaultUserId, companyId, nameMap, descriptionMap, true,
				new ServiceContext());

		Layout layout = layoutPrototype.getLayout();

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		layoutTypePortlet.setLayoutTemplateId(0, layoutTemplateId, false);

		return layout;
	}

}