import * as cypress from "cypress";
/// <reference types="cypress" />

describe('land in login page',()=>{
    it('visit the page',()=>{
        cy.visit("http://localhost:4200/login")
    });

    it('should land up in login component',()=>{
    cy.url().should('include','login');
    cy.wait(2000);
    });

    it('should have login form',()=>{
    cy.get('#logintitle').contains('Please Login Here')
    cy.wait(2000);
    })

})

describe('Using form to login',()=>{

 it('select user type',()=>{
    cy.get('#usertype').click().get('mat-option').contains('Customer').click();
 cy.wait(2000);
 })

 it('enter user name',()=>{
    cy.get('#userName').type("abc")
   cy.wait(2000);
   })

   it('enter password',()=>{
    cy.get('#password').type("123")
   cy.wait(2000);
   })
   
   it('press login',()=>{
    cy.get('#loginbutton').click();
   cy.wait(2000);
   })
})



describe('land in customer dashobard',()=>{

    it('should land up in customer dashobard',()=>{
    cy.url().should('include','/customer/dashboard');
    cy.wait(2000);
    });

})
   describe('go to customer delete',()=>{

    it('press delete',()=>{
        cy.get('#custdeletebtn').click();
        cy.on('window:alert', (str) => {
            expect(str).to.equal('This action cannot be undone. Are you sure you want to delete your profile ?')
          })
          cy.on('window:confirm', () => true)
       cy.wait(2000);
       })

    it('should land up in home component',()=>{
    cy.url().should('include','/home');
    cy.wait(2000);
    });

})
