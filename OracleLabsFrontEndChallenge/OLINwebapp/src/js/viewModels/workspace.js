/**
 * @license
 * Copyright (c) 2014, 2019, Oracle and/or its affiliates.
 * The Universal Permissive License (UPL), Version 1.0
 */
/*
 * Your workspace ViewModel code goes here
 */

const getFormattedArrayFromInput = inputData => inputData.split("\\n").map(e => e.split("\\t"));

const getCategoriesFromRows = rows => rows[0].map((item, index) => `C${index + 1}`);

const getLegendSectionsFromCategories = (categories, attrGroups) => {
  let legendSections = [{ items: [] }];
  let legendItems = legendSections[0].items;
  for (let categoryIndex = 0; categoryIndex < categories.length; categoryIndex++) {
    let category = categories[categoryIndex];
    legendItems.push({ text: category, color: attrGroups.getValue(category), shortDesc: "Filter: " + category });
  }
  return legendSections;
}

const getSeriesFromRows = (rows, attrGroups) => {
  const rowColumn = rows[0];
  const items = rows.slice(1);
  let series = [];
  for (let i = 0; i < rowColumn.length; i++) {
    series.push({
      name: rowColumn[i], items: items.map(item => ({
        value: item[i],
        color: attrGroups.getValue(categories[i]),
        categories: [categories[i]]
      }
      ))
    })
  }
  return series;
}

define(['ojs/ojcore', 'knockout', 'ojs/ojattributegrouphandler', 'jquery', 'ojs/ojchart', 'ojs/ojarraydataprovider', 'ojs/ojselectcombobox', 'ojs/ojinputtext', 'ojs/ojbutton', 'ojs/ojtoolbar', 'ojs/ojlegend'],
  function (oj, ko, attributegrouphandler, $, ojChart, ArrayDataProvider) {

    function WorkspaceViewModel() {

      let self = this;
      self.codeInput = ko.observable('SeriesA\\tSeriesB\\tSeriesC\\n1\\t2\\t3\\n4\\t5\\t6\\n2\\t4\\t3');
      self.executionResult = ko.observable('');
      self.datasource = ko.observableArray([]);


      self.chartStackValue = ko.observable('on');
      self.chartOrientationValue = ko.observable('vertical');
      self.sortingValue = ko.observable('off');
      self.chartTypeOptionValue = ko.observable('bar');
      self.chartGroups = ko.observableArray([]);
      self.hiddenCategoriesValue = ko.observableArray([]);
      self.legendSections = ko.observableArray([]);
      self.dataCursorValue = ko.observable('on');
      self.dataCursorBehaviorValue = ko.observable('snap');

      self.submitButtonAction = function (data, event) {
        const inputData = data.codeInput();
        const attrGroups = new attributegrouphandler.ColorAttributeGroupHandler();
        const rows = getFormattedArrayFromInput(inputData);
        categories = getCategoriesFromRows(rows);
        self.executionResult(inputData);
        self.chartGroups(categories);
        self.datasource(getSeriesFromRows(rows, attrGroups));
        self.legendSections(getLegendSectionsFromCategories(categories, attrGroups));
        return true;
      }


      // Below are a set of the ViewModel methods invoked by the oj-module component.
      // Please reference the oj-module jsDoc for additional information.

      /**
       * Optional ViewModel method invoked after the View is inserted into the
       * document DOM.  The application can put logic that requires the DOM being
       * attached here.
       * This method might be called multiple times - after the View is created
       * and inserted into the DOM and after the View is reconnected
       * after being disconnected.
       */
      self.connected = function () {
        // Implement if needed
      };

      /**
       * Optional ViewModel method invoked after the View is disconnected from the DOM.
       */
      self.disconnected = function () {
        // Implement if needed
      };

      /**
       * Optional ViewModel method invoked after transition to the new View is complete.
       * That includes any possible animation between the old and the new View.
       */
      self.transitionCompleted = function () {
        // Implement if needed
      };
    }

    /*
     * Returns a constructor for the ViewModel so that the ViewModel is constructed
     * each time the view is displayed.  Return an instance of the ViewModel if
     * only one instance of the ViewModel is needed.
     */
    return new WorkspaceViewModel();
  }
);
