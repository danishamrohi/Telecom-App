import * as cypress from "cypress";
/// <reference types="cypress" />

describe('land in home page',()=>{
    it('visit the page',()=>{
        cy.visit("http://localhost:4200/")
    });

it('should land up in home component',()=>{
cy.url().should('include','4200');
cy.wait(2000);
});

it('should have telecom app title',()=>{
cy.get('#title').contains('Welcome to Telecom App')
cy.wait(2000);
})

it('should have telecom app motto',()=>{
    cy.get('#motto').contains('Your Satisfaction Our Priority')
    cy.wait(2000);
    })

it('should have telecom app motto',()=>{
    cy.get('#aboutus').contains('Telecom Application will make your life easy.')
    cy.wait(2000);
    })


describe('checking nav bar buttons',()=>{

 it('check for login button',()=>{
  cy.get('#login').click();
 cy.wait(2000);
 })

it('check for home button',()=>{
cy.get('#home').click();
cy.wait(2000);
})

})

})
