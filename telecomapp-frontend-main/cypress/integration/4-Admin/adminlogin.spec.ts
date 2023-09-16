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
    cy.get('#usertype').click().get('mat-option').contains('Admin').click();
 cy.wait(2000);
 })

 it('enter user name',()=>{
    cy.get('#userName').type("admin")
   cy.wait(2000);
   })

   it('enter password',()=>{
    cy.get('#password').type("password")
   cy.wait(2000);
   })
   
   it('press login',()=>{
    cy.get('#loginbutton').click();
   cy.wait(2000);
   })
})



describe('land in admin dashobard',()=>{

    it('should land up in admin dashobard',()=>{
    cy.url().should('include','/admin/dashboard');
    cy.wait(2000);
    });

})



describe('checking nav bar buttons',()=>{
   
   it('check for home button',()=>{
   cy.get('#home').click();
   cy.wait(2000);
   })
   
   it('check for dashboard button',()=>{
    cy.get('#admindash').click();
   cy.wait(2000);
   })
})

describe('check logout button',()=>{

    it('check for logout button',()=>{
        cy.get('#logout').click();
       cy.wait(2000);
       })

    it('should land up in home',()=>{
    cy.url().should('include','/home');
    cy.wait(2000);
    });

})